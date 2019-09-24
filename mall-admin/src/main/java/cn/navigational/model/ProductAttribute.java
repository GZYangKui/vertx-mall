package cn.navigational.model;

public class ProductAttribute {
    private int id;
    //分类筛选样式：1->普通；1->颜色
    private int filterType;
    //是否支持手动新增；0->不支持；1->支持
    private int handAddStatus;
    //可选值列表，以逗号隔开
    private String inputList;
    //属性录入方式：0->手工录入；1->从列表中选取
    private int inputType;
    private String name;
    //属性分类id
    private int productAttributeCategoryId;
    //关联状态
    private int relatedStatus;
    //检索类型；0->不需要进行检索；1->关键字检索；2->范围检索
    private int searchType;
    //属性选择类型：0->唯一；1->单选；2->多选
    private int selectType;

    private long sort;
    //0->规格；1->参数
    private int type;

    public int getFilterType() {
        return filterType;
    }

    public void setFilterType(int filterType) {
        this.filterType = filterType;
    }

    public int getHandAddStatus() {
        return handAddStatus;
    }

    public void setHandAddStatus(int handAddStatus) {
        this.handAddStatus = handAddStatus;
    }

    public String getInputList() {
        return inputList;
    }

    public void setInputList(String inputList) {
        this.inputList = inputList;
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductAttributeCategoryId() {
        return productAttributeCategoryId;
    }

    public void setProductAttributeCategoryId(int productAttributeCategoryId) {
        this.productAttributeCategoryId = productAttributeCategoryId;
    }

    public int getRelatedStatus() {
        return relatedStatus;
    }

    public void setRelatedStatus(int relatedStatus) {
        this.relatedStatus = relatedStatus;
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public int getSelectType() {
        return selectType;
    }

    public void setSelectType(int selectType) {
        this.selectType = selectType;
    }

    public long getSort() {
        return sort;
    }

    public void setSort(long sort) {
        this.sort = sort;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
