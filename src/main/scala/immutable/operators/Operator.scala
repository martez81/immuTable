package immutable.operators

import immutable.LoggerHelper._
import immutable.{Config, Table}
import immutable._

import scala.util.{Try, Failure, Success}

/**
  * Created by marcin on 2/26/16.
  */
object Operator {
    def prepareBuffer[A](col: Column[A], table: Table): Unit = {
        val ext = col match {
            case x: VarCharColumn => {
                if (x.encoder == 'Dense) "densevar"
                else if (x.encoder == 'Dict) "dict"
            }
            case x: FixedCharColumn => {
                if (x.encoder == 'Dense) "dense"
                else if (x.encoder == 'RunLength) "rle"
                else if (x.encoder == 'Dict) "dict"
            }
            case x: NumericColumn[_] => {
                if (x.encoder == 'Dense) "dense"
                else if (x.encoder == 'DensePage) "densep"
                else if (x.encoder == 'RunLength) "rle"
            }
        }

        Try(BufferManager.get(col.name)) match {
            case Success(x) => info(s"Buffer ${col.name} already registered.")
            case Failure(e) => {
                info(s"Registering new buffer: ${col.name}")
                BufferManager.registerFromFile(col.name, s"${Config.home}/${table.name}/${col.name}.${ext}", col.size)
            }
        }
    }
}
//
//object SelectSpecial extends Operator {
//    def apply[A](col: Column[A], exact: String)(implicit table: Table): ByteBuffer = {
//        val datFile = BufferManager.get(col.name)
//        datFile.position(0)
//
//        val fileSize = col.size * table.size
//        var bytes = new Array[Byte](4)
//        var result = ByteBuffer.allocate(table.size)
//        var counter = 0
//
//        val exactVal = exact.toInt
//
//        while (counter < table.size) {
//            datFile.get(bytes)
//            val value = Conversions.bytesToInt(bytes)
//
//            if (value == exactVal) {
//                result.putInt(counter - 1)
//            }
//            counter += 1
//        }
//        result.flip
//        result
//    }
//}
