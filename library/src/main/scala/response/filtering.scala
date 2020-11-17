package unfiltered.response

import java.io.OutputStream
import java.util.zip.{GZIPOutputStream => GZOS}


/** Enclose the response's output stream in another stream,
 * typically a subclass of java.io.FilterOutputStream */
object ResponseFilter {
  trait Filtering[S <: OutputStream] extends ResponseFunction[Any] {
    def apply[T](d: HttpResponse[T]) = {
      new DelegatingResponse(d) {
        override val outputStream = filter(d.outputStream)
      }
    }
    def filter(os: OutputStream): S
  }
  object GZip extends Filtering[GZOS] {
    def filter(os: OutputStream) = new GZOS(os)
  }
}
