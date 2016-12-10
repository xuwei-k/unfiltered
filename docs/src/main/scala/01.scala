package page01

object Example1 {

// #example01
import unfiltered.request._
import unfiltered.response._

val echo = unfiltered.filter.Planify {
  case Path(Seg(p :: Nil)) => ResponseString(p)
}
// #example01


// #example02
unfiltered.jetty.Server.anylocal.plan(echo).run()
// #example02


// #example03
val echoNice = unfiltered.filter.Planify {
  case Path(Seg(p :: Nil)) => ResponseString(p)
  case _ => ResponseString(
    "I can echo exactly one path element."
  )
}
unfiltered.jetty.Server.anylocal.plan(echoNice).run()
// #example03


// #example04
val nice = unfiltered.filter.Planify {
  case _ => ResponseString(
    "I can echo exactly one path element."
  )
}
unfiltered.jetty.Server.anylocal.plan(echo).plan(nice).run()
// #example04

}
