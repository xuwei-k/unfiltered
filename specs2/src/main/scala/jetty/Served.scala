package unfiltered.specs2
package jetty

import org.specs2.mutable._
import org.specs2.specification.core.Fragments

trait Planned extends Served {

  def setup = _.plan(unfiltered.filter.Planify(intent))

  def intent[A, B]: unfiltered.Cycle.Intent[A, B]
}

trait Served extends Hosted with SpecificationLike {

  import unfiltered.jetty._

  def setup: (Server => Server)

  lazy val server = setup(Server.http(port))

  override def after: Fragments = {
    server.stop()
    server.destroy()
    super.after
  }

  override def before: Fragments = {
    server.start()
    super.before
  }
}
