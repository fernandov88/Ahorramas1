package com.example.mario.ahorramas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Productos {

    /* INICIA ORACLE*/

    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<>();

    /*
    @SerializedName("first")
    @Expose
    private First first;
    */

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    /*
    public First getFirst() {
        return first;
    }

    public void setFirst(First first) {
        this.first = first;
    }
    */


    /* FIN ORACLE ORACLE*/

    /*
    @SerializedName("ProductID")
        @Expose
        private Integer productID;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("ProductNumber")
        @Expose
        private String productNumber;
        @SerializedName("MakeFlag")
        @Expose
        private Boolean makeFlag;
        @SerializedName("FinishedGoodsFlag")
        @Expose
        private Boolean finishedGoodsFlag;
        @SerializedName("Color")
        @Expose
        private Object color;
        @SerializedName("SafetyStockLevel")
        @Expose
        private Double safetyStockLevel;
        @SerializedName("ReorderPoint")
        @Expose
        private Double reorderPoint;
        @SerializedName("StandardCost")
        @Expose
        private Double standardCost;
        @SerializedName("ListPrice")
        @Expose
        private Double listPrice;
        @SerializedName("Size")
        @Expose
        private Object size;
        @SerializedName("SizeUnitMeasureCode")
        @Expose
        private Object sizeUnitMeasureCode;
        @SerializedName("WeightUnitMeasureCode")
        @Expose
        private Object weightUnitMeasureCode;
        @SerializedName("Weight")
        @Expose
        private Object weight;
        @SerializedName("DaysToManufacture")
        @Expose
        private Double daysToManufacture;
        @SerializedName("ProductLine")
        @Expose
        private Object productLine;
        @SerializedName("Class")
        @Expose
        private Object _class;
        @SerializedName("Style")
        @Expose
        private Object style;
        @SerializedName("ProductSubcategoryID")
        @Expose
        private Object productSubcategoryID;
        @SerializedName("ProductModelID")
        @Expose
        private Object productModelID;
        @SerializedName("SellStartDate")
        @Expose
        private String sellStartDate;
        @SerializedName("SellEndDate")
        @Expose
        private Object sellEndDate;
        @SerializedName("DiscontinuedDate")
        @Expose
        private Object discontinuedDate;
        @SerializedName("ModifiedDate")
        @Expose
        private String modifiedDate;

        public Integer getProductID() {
            return productID;
        }

        public void setProductID(Integer productID) {
            this.productID = productID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProductNumber() {
            return productNumber;
        }

        public void setProductNumber(String productNumber) {
            this.productNumber = productNumber;
        }

        public Boolean getMakeFlag() {
            return makeFlag;
        }

        public void setMakeFlag(Boolean makeFlag) {
            this.makeFlag = makeFlag;
        }

        public Boolean getFinishedGoodsFlag() {
            return finishedGoodsFlag;
        }

        public void setFinishedGoodsFlag(Boolean finishedGoodsFlag) {
            this.finishedGoodsFlag = finishedGoodsFlag;
        }

        public Object getColor() {
            return color;
        }

        public void setColor(Object color) {
            this.color = color;
        }

        public Double getSafetyStockLevel() {
            return safetyStockLevel;
        }

        public void setSafetyStockLevel(Double safetyStockLevel) {
            this.safetyStockLevel = safetyStockLevel;
        }

        public Double getReorderPoint() {
            return reorderPoint;
        }

        public void setReorderPoint(Double reorderPoint) {
            this.reorderPoint = reorderPoint;
        }

        public Double getStandardCost() {
            return standardCost;
        }

        public void setStandardCost(Double standardCost) {
            this.standardCost = standardCost;
        }

        public Double getListPrice() {
            return listPrice;
        }

        public void setListPrice(Double listPrice) {
            this.listPrice = listPrice;
        }

        public Object getSize() {
            return size;
        }

        public void setSize(Object size) {
            this.size = size;
        }

        public Object getSizeUnitMeasureCode() {
            return sizeUnitMeasureCode;
        }

        public void setSizeUnitMeasureCode(Object sizeUnitMeasureCode) {
            this.sizeUnitMeasureCode = sizeUnitMeasureCode;
        }

        public Object getWeightUnitMeasureCode() {
            return weightUnitMeasureCode;
        }

        public void setWeightUnitMeasureCode(Object weightUnitMeasureCode) {
            this.weightUnitMeasureCode = weightUnitMeasureCode;
        }

        public Object getWeight() {
            return weight;
        }

        public void setWeight(Object weight) {
            this.weight = weight;
        }

        public Double getDaysToManufacture() {
            return daysToManufacture;
        }

        public void setDaysToManufacture(Double daysToManufacture) {
            this.daysToManufacture = daysToManufacture;
        }

        public Object getProductLine() {
            return productLine;
        }

        public void setProductLine(Object productLine) {
            this.productLine = productLine;
        }

        public Object getClass_() {
            return _class;
        }

        public void setClass_(Object _class) {
            this._class = _class;
        }

        public Object getStyle() {
            return style;
        }

        public void setStyle(Object style) {
            this.style = style;
        }

        public Object getProductSubcategoryID() {
            return productSubcategoryID;
        }

        public void setProductSubcategoryID(Object productSubcategoryID) {
            this.productSubcategoryID = productSubcategoryID;
        }

        public Object getProductModelID() {
            return productModelID;
        }

        public void setProductModelID(Object productModelID) {
            this.productModelID = productModelID;
        }

        public String getSellStartDate() {
            return sellStartDate;
        }

        public void setSellStartDate(String sellStartDate) {
            this.sellStartDate = sellStartDate;
        }

        public Object getSellEndDate() {
            return sellEndDate;
        }

        public void setSellEndDate(Object sellEndDate) {
            this.sellEndDate = sellEndDate;
        }

        public Object getDiscontinuedDate() {
            return discontinuedDate;
        }

        public void setDiscontinuedDate(Object discontinuedDate) {
            this.discontinuedDate = discontinuedDate;
        }

        public String getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(String modifiedDate) {
            this.modifiedDate = modifiedDate;
        }
        */
/*

    @SerializedName("CODIGO")
    @Expose
    private Integer cODIGO;
    @SerializedName("NOMBRE")
    @Expose
    private String nOMBRE;
    @SerializedName("PRECIO")
    @Expose
    private Double pRECIO;
    @SerializedName("EXISTENCIA")
    @Expose
    private Integer eXISTENCIA;

    public Integer getCODIGO() {
        return cODIGO;
    }

    public void setCODIGO(Integer cODIGO) {
        this.cODIGO = cODIGO;
    }

    public String getNOMBRE() {
        return nOMBRE;
    }

    public void setNOMBRE(String nOMBRE) {
        this.nOMBRE = nOMBRE;
    }

    public Double getPRECIO() {
        return pRECIO;
    }

    public void setPRECIO(Double pRECIO) {
        this.pRECIO = pRECIO;
    }

    public Integer getEXISTENCIA() {
        return eXISTENCIA;
    }

    public void setEXISTENCIA(Integer eXISTENCIA) {
        this.eXISTENCIA = eXISTENCIA;
    }
*/
/*
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("precio")
    @Expose
    private Integer precio;
    @SerializedName("existencia")
    @Expose
    private Integer existencia;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }
*/
}