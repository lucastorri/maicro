package maicro.example

import play.api.libs.json.JsValue

object Example {

  def main(args: Array[String]): Unit = {

    import scala.concurrent.ExecutionContext.Implicits.global

    import maicro.serve
    import maicro.log._
    import maicro.error._
    import maicro.router._
    import maicro.format._

    val server = serve(log(handleErrors(route(
      get("/hi", _ => send(s"Hi!")),
      get("/hi/:name", req => send(s"Hello ${req.params("name")}")),
      post("/hi", req => send(s"Hey ${(receive[JsValue](req) \ "name").as[String]}"))
    ))))

    sys.addShutdownHook {
      server.stop()
    }

    server.start()

  }

}
