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
import un.unece.uncefact.data.standard.unqualifieddatatype._31.CodeType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.IDType;


/**
 * <p>CIILBSubordinateTradeLineItemType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CIILBSubordinateTradeLineItemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}IDType" minOccurs="0"/>
 *         &lt;element name="CategoryCode" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}CodeType"/>
 *         &lt;element name="SpecifiedCIILBSupplyChainTradeAgreement" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIILBSupplyChainTradeAgreementType"/>
 *         &lt;element name="SpecifiedCIILBSupplyChainTradeDelivery" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIILBSupplyChainTradeDeliveryType"/>
 *         &lt;element name="SpecifiedCIILBSupplyChainTradeSettlement" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIILBSupplyChainTradeSettlementType"/>
 *         &lt;element name="ApplicableCITradeProduct" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CITradeProductType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIILBSubordinateTradeLineItemType", propOrder = {
    "id",
    "categoryCode",
    "specifiedCIILBSupplyChainTradeAgreement",
    "specifiedCIILBSupplyChainTradeDelivery",
    "specifiedCIILBSupplyChainTradeSettlement",
    "applicableCITradeProduct"
})
public class CIILBSubordinateTradeLineItemType {

    @XmlElement(name = "ID")
    protected IDType id;
    @XmlElement(name = "CategoryCode", required = true)
    protected CodeType categoryCode;
    @XmlElement(name = "SpecifiedCIILBSupplyChainTradeAgreement", required = true)
    protected CIILBSupplyChainTradeAgreementType specifiedCIILBSupplyChainTradeAgreement;
    @XmlElement(name = "SpecifiedCIILBSupplyChainTradeDelivery", required = true)
    protected CIILBSupplyChainTradeDeliveryType specifiedCIILBSupplyChainTradeDelivery;
    @XmlElement(name = "SpecifiedCIILBSupplyChainTradeSettlement", required = true)
    protected CIILBSupplyChainTradeSettlementType specifiedCIILBSupplyChainTradeSettlement;
    @XmlElement(name = "ApplicableCITradeProduct", required = true)
    protected CITradeProductType applicableCITradeProduct;

    /**
     * idプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link IDType }
     *     
     */
    public IDType getID() {
        return id;
    }

    /**
     * idプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link IDType }
     *     
     */
    public void setID(IDType value) {
        this.id = value;
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
     * specifiedCIILBSupplyChainTradeAgreementプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIILBSupplyChainTradeAgreementType }
     *     
     */
    public CIILBSupplyChainTradeAgreementType getSpecifiedCIILBSupplyChainTradeAgreement() {
        return specifiedCIILBSupplyChainTradeAgreement;
    }

    /**
     * specifiedCIILBSupplyChainTradeAgreementプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIILBSupplyChainTradeAgreementType }
     *     
     */
    public void setSpecifiedCIILBSupplyChainTradeAgreement(CIILBSupplyChainTradeAgreementType value) {
        this.specifiedCIILBSupplyChainTradeAgreement = value;
    }

    /**
     * specifiedCIILBSupplyChainTradeDeliveryプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIILBSupplyChainTradeDeliveryType }
     *     
     */
    public CIILBSupplyChainTradeDeliveryType getSpecifiedCIILBSupplyChainTradeDelivery() {
        return specifiedCIILBSupplyChainTradeDelivery;
    }

    /**
     * specifiedCIILBSupplyChainTradeDeliveryプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIILBSupplyChainTradeDeliveryType }
     *     
     */
    public void setSpecifiedCIILBSupplyChainTradeDelivery(CIILBSupplyChainTradeDeliveryType value) {
        this.specifiedCIILBSupplyChainTradeDelivery = value;
    }

    /**
     * specifiedCIILBSupplyChainTradeSettlementプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIILBSupplyChainTradeSettlementType }
     *     
     */
    public CIILBSupplyChainTradeSettlementType getSpecifiedCIILBSupplyChainTradeSettlement() {
        return specifiedCIILBSupplyChainTradeSettlement;
    }

    /**
     * specifiedCIILBSupplyChainTradeSettlementプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIILBSupplyChainTradeSettlementType }
     *     
     */
    public void setSpecifiedCIILBSupplyChainTradeSettlement(CIILBSupplyChainTradeSettlementType value) {
        this.specifiedCIILBSupplyChainTradeSettlement = value;
    }

    /**
     * applicableCITradeProductプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CITradeProductType }
     *     
     */
    public CITradeProductType getApplicableCITradeProduct() {
        return applicableCITradeProduct;
    }

    /**
     * applicableCITradeProductプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CITradeProductType }
     *     
     */
    public void setApplicableCITradeProduct(CITradeProductType value) {
        this.applicableCITradeProduct = value;
    }

}
