package maicro.server

import java.io.InputStream
import java.net.InetSocketAddress

import com.sun.net.httpserver.{HttpExchange, HttpHandler, HttpServer}
import maicro.{Request, Response, Server}

import scala.concurrent.Await
import scala.concurrent.duration._

class JavaServer(handler: maicro.Handler) extends Server {

  private var server = Option.empty[HttpServer]

  override def start(port: Int): Unit = {
    val server = HttpServer.create(new InetSocketAddress(port), 0)
    server.createContext("/", MainHandler)
    server.start()
    this.server = Some(server)
  }

  override def stop(): Unit = {
    server.foreach(_.stop(0))
  }

  private object MainHandler extends HttpHandler {

    override def handle(request: HttpExchange): Unit = {

      val req = new Request {
        override def method: String = request.getRequestMethod

        override def path: String = request.getRequestURI.getPath

        override def input: InputStream = request.getRequestBody
      }

      val res = Await.result(handler(req), 10.seconds)

      request.getResponseHeaders.add("Content-Type", res.contentType)
      request.sendResponseHeaders(res.status, 0)
      copy(res, request)
    }

    private def copy(res: Response, request: HttpExchange): Unit = {
      val input = res.output
      val output = request.getResponseBody

      Stream.continually(input.read()).takeWhile(_ != -1).foreach(output.write)

      output.close()
      input.close()
    }

  }

}
