package cn.navigational.routers;

import cn.navigational.annotation.Router;
import cn.navigational.annotation.RouterMapping;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.model.Paging;
import cn.navigational.model.ProductAttribute;
import cn.navigational.service.ProductAttributeService;
import cn.navigational.service.impl.ProductAttributeServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static cn.navigational.config.Constants.TOTAL;
import static cn.navigational.utils.Assert.isEmpty;
import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.ResponseUtils.responseSuccessJson;
import static cn.navigational.utils.StringUtils.getIdFromQuery;

/**
 * 商品属性管理接口
 *
 * @author YangKui
 * @since 1.0
 */

@Verticle
@Router(api = "/api/productAttribute")
public class ProductAttributeRouter extends RouterVerticle {
    private ProductAttributeService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new ProductAttributeServiceImpl(vertx, config());
    }

    @RouterMapping(api = "/category/list", description = "获取属性分类")
    public void listAttributeCategory(final EBRequest request, final Promise<JsonObject> response) {
        JsonObject query = request.getQuery();
        long pageNum = -1;
        long pageSize = -1;
        if (query.containsKey("pageNum")) {
            pageNum = Long.parseLong(query.getString("pageNum"));
        }
        if (query.containsKey("pageSize")) {
            pageSize = Long.parseLong(query.getString("pageSize"));
        }
        List<JsonObject> list = new ArrayList<>();
        service.listCategory(pageNum, pageSize).compose(rs -> {
            list.addAll(rs);
            return service.countAttrCate();
        }).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("获取分类列表失败", 200));
                return;
            }
            JsonObject rs = responseSuccessJson(list);
            rs.put(TOTAL, ar.result());
            response.complete(rs);
        });
    }

    @RouterMapping(api = "/category/create", method = HttpMethod.POST, description = "创建分类")
    public void categoryCreate(final EBRequest request, final Promise<JsonObject> response) {
        String name = request.getSingleRequestParam("name");
        if (isEmpty(name)) {
            response.complete(responseFailed("分类名称不能为空", 200));
            return;
        }

        service.categoryDetail(name).compose(optional -> {
            if (optional.isPresent()) {
                return Future.failedFuture("分类已存在");
            }
            return service.createCategory(name);
        }).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("新增分类失败(分类可能已经存在)", 200));
                return;
            }
            response.complete(responseSuccessJson());
        });
    }

    @RouterMapping(api = "/category/delete", method = HttpMethod.GET, description = "删除分分类")
    public void deleteCate(final EBRequest request, final Promise<JsonObject> response) {
        String temp = request.getQuery("cateId");
        if (isEmpty(temp)) {
            response.complete(responseFailed("请求参数缺失", 200));
            return;
        }
        int cateId = Integer.parseInt(temp);
        service.deleteCategory(cateId).compose(r -> {
            if (r > 0)
                return service.deleteCateAttr(cateId);
            else
                return Future.failedFuture("删除分类失败");
        }).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("删除分类失败", 200));
                return;
            }
            response.complete(responseSuccessJson());
        });
    }

    @RouterMapping(api = "/category/update", method = HttpMethod.POST, description = "更新分类信息")
    public void updateCate(final EBRequest request, final Promise<JsonObject> response) {
        String var1 = request.getQuery("cateId");
        String name = request.getSingleRequestParam("name");
        if (isEmpty(var1) || isEmpty(name)) {
            response.complete(responseFailed("请求参数缺失", 200));
            return;
        }
        int cateId = Integer.parseInt(var1);
        service.updateCate(cateId, name).setHandler(ar -> {
            if (ar.failed() || ar.result() <= 0) {
                response.complete(responseFailed("更新分类失败", 200));
                return;
            }
            response.complete(responseSuccessJson());
        });
    }

    @RouterMapping(api = "/list", description = "获取某个分类下的属性/参数")
    public void list(final EBRequest request, final Promise<JsonObject> response) {
        int pageIndex = 1;
        int pageSize = 10;
        int cId;
        int type;
        String var1 = request.getQuery("pageNum");
        String var2 = request.getQuery("pageSize");
        String var3 = request.getQuery("cid");
        String var4 = request.getQuery("type");
        if (isEmpty(var3) || isEmpty(var4)) {
            response.complete(responseFailed("缺少请求参数", 200));
            return;
        }
        cId = Integer.parseInt(var3);
        type = Integer.parseInt(var4);
        if (!isEmpty(var1))
            pageIndex = Integer.parseInt(var1);
        if (!isEmpty(var2))
            pageSize = Integer.parseInt(var2);
        Paging page = new Paging(pageIndex, pageSize);

        List<JsonObject> list = new ArrayList<>();
        service.list(cId, type, page).compose((rs) -> {
            list.addAll(rs);
            return service.countAttrWithType(cId, type);
        }).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("获取属性/参数失败", 200));
                return;
            }
            JsonObject rs = responseSuccessJson(list);
            rs.put(TOTAL, ar.result());
            response.complete(rs);
        });

    }

    @RouterMapping(api = "/create", method = HttpMethod.POST, description = "创建规格/参数")
    public void createAttr(final EBRequest request, final Promise<JsonObject> response) {
        ProductAttribute attr = request.getBodyAsJson().mapTo(ProductAttribute.class);
        int cateId = attr.getProductAttributeCategoryId();
        int type = attr.getType();
        service.getProductAttribute(attr).compose(r -> {
            if (r.isPresent()) {
                return Future.failedFuture("规格/参数已经存在");
            }
            return service.createAttribute(attr);
        }).compose(r ->
                service.changeCateChildrenNum(cateId, type, 1, Promise.promise(), 0)).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed(ar.cause().getMessage(), 200));
                return;
            }
            response.complete(responseSuccessJson());
        });

    }

    @RouterMapping(api = "/delete", method = HttpMethod.POST, description = "删除某个属性")
    public void delete(final EBRequest request, final Promise<JsonObject> response) {
        String var1 = request.getSingleRequestParam("ids");
        if (isEmpty(var1)) {
            response.complete(responseFailed("缺少请求参数", 200));
            return;
        }
        List<Integer> ids = getIdFromQuery(var1);
        service.listAttr(ids).compose(r -> {
            //减去分类参数/规格数量
            service.batchUpdateAttrCate(r, -1);
            //删除属性/规格
            return service.deleteAttr(ids);
        }).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("删除属性/参数失败", 200));
                return;
            }
            response.complete(responseSuccessJson());
        });
    }

    @RouterMapping(api = "/detail", method = HttpMethod.GET, description = "获取属性/规格详情")
    public void detail(final EBRequest request, final Promise<JsonObject> response) {
        String var1 = request.getQuery("attrId");
        if (isEmpty(var1)) {
            response.complete(responseFailed("参数缺失", 200));
            return;
        }
        int attrId = Integer.parseInt(var1);
        service.getAttr(attrId).setHandler(ar -> {
            if (ar.failed()) {
                response.fail(ar.cause());
                return;
            }
            Optional<JsonObject> optional = ar.result();
            if (optional.isEmpty()) {
                response.complete(responseFailed("参数/规格不存在", 200));
            } else {
                response.complete(responseSuccessJson(optional.get()));
            }
        });
    }

    @RouterMapping(api = "/update", method = HttpMethod.POST, description = "更新属性/规格")
    public void update(final EBRequest request, final Promise<JsonObject> response) {
        ProductAttribute attr = request.getBodyAsJson().mapTo(ProductAttribute.class);
        List<ProductAttribute> attrs = new ArrayList<>();
        service.getAttr(attr.getId()).compose(r -> {
            if (r.isEmpty()) {
                return Future.failedFuture("属性不存在");
            }
            attrs.add(r.get().mapTo(ProductAttribute.class));
            return service.updateAttr(attr);
        }).compose(r -> {
            if (r <= 0) {
                return Future.failedFuture("更新失败");
            }
            ///对比分类是否发生改变,如果发生改变,则改变对应分类属性/规格数目///
            ProductAttribute attr1 = attrs.get(0);
            if (attr1.getProductAttributeCategoryId() != attr.getProductAttributeCategoryId()) {
                service.changeCateChildrenNum(attr.getProductAttributeCategoryId(), attr.getType(), 1, Promise.promise(), 1);
                service.changeCateChildrenNum(attr1.getProductAttributeCategoryId(), attr1.getType(), -1, Promise.promise(), 1);
            }
            return Future.succeededFuture();
        }).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed(ar.cause().getMessage(), 200));
                return;
            }
            response.complete(responseSuccessJson());
        });
    }
}
