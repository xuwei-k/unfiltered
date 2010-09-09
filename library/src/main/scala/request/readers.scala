package unfiltered.request


object InStream {
  def unapply(req: HttpRequest) = Some(req.getInputStream, req)
}

object Read {
  def unapply(req: HttpRequest) = Some(req.getReader, req)
}

object Bytes {
  def unapply(req: HttpRequest) = {
    val InStream(in, _) = req
    val bos = new java.io.ByteArrayOutputStream
    val ba = new Array[Byte](4096)
    /* @scala.annotation.tailrec */ def read {
      val len = in.read(ba)
      if (len > 0) bos.write(ba, 0, len)
      if (len >= 0) read
    }
    read
    in.close
    bos.toByteArray match {
      case Array() => None
      case ba => Some(ba, req)
    }
  }
}