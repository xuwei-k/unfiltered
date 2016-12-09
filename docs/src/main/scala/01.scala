package page01

object Example1 {

// #example01
import unfiltered.request._
import unfiltered.response._

val echo = unfiltered.filter.Planify {
  case Path(Seg(p :: Nil)) => ResponseString(p)
}
// #example01

}
