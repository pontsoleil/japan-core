//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2023.01.16 時間 12:39:23 PM JST 
//


package un.unece.uncefact.data.standard.reusableaggregatebusinessinformationentity._31;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.QuantityType;


/**
 * <p>CIILBSupplyChainTradeDeliveryType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CIILBSupplyChainTradeDeliveryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PackageQuantity" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}QuantityType" minOccurs="0"/>
 *         &lt;element name="ProductUnitQuantity" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}QuantityType" minOccurs="0"/>
 *         &lt;element name="PerPackageUnitQuantity" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}QuantityType" minOccurs="0"/>
 *         &lt;element name="BilledQuantity" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}QuantityType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIILBSupplyChainTradeDeliveryType", propOrder = {
    "packageQuantity",
    "productUnitQuantity",
    "perPackageUnitQuantity",
    "billedQuantity"
})
public class CIILBSupplyChainTradeDeliveryType {

    @XmlElement(name = "PackageQuantity")
    protected QuantityType packageQuantity;
    @XmlElement(name = "ProductUnitQuantity")
    protected QuantityType productUnitQuantity;
    @XmlElement(name = "PerPackageUnitQuantity")
    protected QuantityType perPackageUnitQuantity;
    @XmlElement(name = "BilledQuantity", required = true)
    protected QuantityType billedQuantity;

    /**
     * packageQuantityプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link QuantityType }
     *     
     */
    public QuantityType getPackageQuantity() {
        return packageQuantity;
    }

    /**
     * packageQuantityプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link QuantityType }
     *     
     */
    public void setPackageQuantity(QuantityType value) {
        this.packageQuantity = value;
    }

    /**
     * productUnitQuantityプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link QuantityType }
     *     
     */
    public QuantityType getProductUnitQuantity() {
        return productUnitQuantity;
    }

    /**
     * productUnitQuantityプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link QuantityType }
     *     
     */
    public void setProductUnitQuantity(QuantityType value) {
        this.productUnitQuantity = value;
    }

    /**
     * perPackageUnitQuantityプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link QuantityType }
     *     
     */
    public QuantityType getPerPackageUnitQuantity() {
        return perPackageUnitQuantity;
    }

    /**
     * perPackageUnitQuantityプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link QuantityType }
     *     
     */
    public void setPerPackageUnitQuantity(QuantityType value) {
        this.perPackageUnitQuantity = value;
    }

    /**
     * billedQuantityプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link QuantityType }
     *     
     */
    public QuantityType getBilledQuantity() {
        return billedQuantity;
    }

    /**
     * billedQuantityプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link QuantityType }
     *     
     */
    public void setBilledQuantity(QuantityType value) {
        this.billedQuantity = value;
    }

}
