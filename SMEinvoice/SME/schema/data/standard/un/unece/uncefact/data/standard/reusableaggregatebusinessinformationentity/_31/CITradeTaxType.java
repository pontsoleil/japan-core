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
import un.unece.uncefact.data.standard.unqualifieddatatype._31.AmountType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.CodeType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.IDType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.PercentType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.TextType;


/**
 * <p>CITradeTaxType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CITradeTaxType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TypeCode" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}CodeType" minOccurs="0"/>
 *         &lt;element name="BasisAmount" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}AmountType" minOccurs="0"/>
 *         &lt;element name="CategoryCode" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}CodeType"/>
 *         &lt;element name="CategoryName" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}TextType" minOccurs="0"/>
 *         &lt;element name="RateApplicablePercent" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}PercentType"/>
 *         &lt;element name="GrandTotalAmount" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}AmountType" minOccurs="0"/>
 *         &lt;element name="LocalTaxSystemID" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}IDType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CITradeTaxType", propOrder = {
    "typeCode",
    "basisAmount",
    "categoryCode",
    "categoryName",
    "rateApplicablePercent",
    "grandTotalAmount",
    "localTaxSystemID"
})
public class CITradeTaxType {

    @XmlElement(name = "TypeCode")
    protected CodeType typeCode;
    @XmlElement(name = "BasisAmount")
    protected AmountType basisAmount;
    @XmlElement(name = "CategoryCode", required = true)
    protected CodeType categoryCode;
    @XmlElement(name = "CategoryName")
    protected TextType categoryName;
    @XmlElement(name = "RateApplicablePercent", required = true)
    protected PercentType rateApplicablePercent;
    @XmlElement(name = "GrandTotalAmount")
    protected AmountType grandTotalAmount;
    @XmlElement(name = "LocalTaxSystemID")
    protected IDType localTaxSystemID;

    /**
     * typeCodeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getTypeCode() {
        return typeCode;
    }

    /**
     * typeCodeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setTypeCode(CodeType value) {
        this.typeCode = value;
    }

    /**
     * basisAmountプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getBasisAmount() {
        return basisAmount;
    }

    /**
     * basisAmountプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setBasisAmount(AmountType value) {
        this.basisAmount = value;
    }

    /**
     * categoryCodeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getCategoryCode() {
        return categoryCode;
    }

    /**
     * categoryCodeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setCategoryCode(CodeType value) {
        this.categoryCode = value;
    }

    /**
     * categoryNameプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getCategoryName() {
        return categoryName;
    }

    /**
     * categoryNameプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setCategoryName(TextType value) {
        this.categoryName = value;
    }

    /**
     * rateApplicablePercentプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link PercentType }
     *     
     */
    public PercentType getRateApplicablePercent() {
        return rateApplicablePercent;
    }

    /**
     * rateApplicablePercentプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link PercentType }
     *     
     */
    public void setRateApplicablePercent(PercentType value) {
        this.rateApplicablePercent = value;
    }

    /**
     * grandTotalAmountプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getGrandTotalAmount() {
        return grandTotalAmount;
    }

    /**
     * grandTotalAmountプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setGrandTotalAmount(AmountType value) {
        this.grandTotalAmount = value;
    }

    /**
     * localTaxSystemIDプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link IDType }
     *     
     */
    public IDType getLocalTaxSystemID() {
        return localTaxSystemID;
    }

    /**
     * localTaxSystemIDプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link IDType }
     *     
     */
    public void setLocalTaxSystemID(IDType value) {
        this.localTaxSystemID = value;
    }

}
