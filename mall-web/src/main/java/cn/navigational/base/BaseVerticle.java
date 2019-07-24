package cn.navigational.base;

import io.vertx.core.AbstractVerticle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseVerticle extends AbstractVerticle {
    protected final Logger logger =  LogManager.getLogger();

}
