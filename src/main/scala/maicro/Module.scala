package maicro

import _root_.maicro.server.JavaServer

trait Module {

  def serve(handler: Handler): Server = {
    new JavaServer(handler)
  }

}
