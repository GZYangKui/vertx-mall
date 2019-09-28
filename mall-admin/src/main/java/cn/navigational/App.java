package cn.navigational;

import cn.navigational.annotation.Application;
import cn.navigational.annotation.ScanPackage;
import cn.navigational.base.VertxApplication;


@Application(configs = {"/home/yangkui/.vert.x-mall/global_config.json", "config/local_config.json"})
@ScanPackage(packages = {"cn.navigational.api","cn.navigational.routers"})
public class App extends VertxApplication {
    public static void main(String[] args) {
        new App().init();
    }
}
