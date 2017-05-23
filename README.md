# `maicro`

`maicro`, short for Maicro Douglas, is a Scala copy of [micro](https://github.com/zeit/micro).

```scala
import maicro.serve
import maicro.router._
import maicro.format._

val server = serve(route(
  get("/hi", _ => send(s"Hi!"))
))

server.start()
```

You can see a more complete example [here](src/main/scala/maicro/example/Example.scala).
