package maicro.format

import maicro.Request

trait Reader[C] {
  def read(req: Request): C
}
