package maicro

import scala.concurrent.Future

package object router extends router.Module {

  type ParameterizedHandler = (RequestWithParams) => Future[Response]

}
