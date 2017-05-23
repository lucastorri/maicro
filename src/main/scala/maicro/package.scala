import scala.concurrent.Future

package object maicro extends maicro.Module {

  type Handler = Request => Future[Response]

}
