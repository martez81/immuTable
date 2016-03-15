package immutable

import org.apache.log4j.BasicConfigurator
import org.apache.log4j.Level
import org.apache.log4j.Logger

/**
  * Created by marcin on 2/5/16.
  */
object LoggerHelper {
    BasicConfigurator.configure()

    val log = Logger.getLogger("immutable")

    Config.loggerLevel match {
        case 'DEBUG => log.setLevel(Level.DEBUG)
        case 'INFO => log.setLevel(Level.INFO)
        case 'WARN => log.setLevel(Level.WARN)
        case 'ERROR => log.setLevel(Level.ERROR)
    }

    def info(message: String) = log.info(message)
    def warn(message: String) = log.warn(message)
    def error(message: String) = log.error(message)
    def debug(message: String) = log.debug(message)
}
