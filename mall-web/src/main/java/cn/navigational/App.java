package cn.navigational;

import cn.navigational.annotation.ScanPackage;

import cn.navigational.base.VertxApplication;
import cn.navigational.annotation.Application;


@Application(config = "config/config.json", api = "config/api.json")
@ScanPackage(packages = {"cn.navigational.api", "cn.navigational.routers"})
public class App extends VertxApplication {
    public static void main(String[] args) { new App().init(); }
}
