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


/**
 * <p>CIIHSupplyChainTradeAgreementType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CIIHSupplyChainTradeAgreementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SellerCITradeParty" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CITradePartyType"/>
 *         &lt;element name="BuyerCITradeParty" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CITradePartyType"/>
 *         &lt;element name="SpecifiedProcuringProject" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}ProcuringProjectType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIIHSupplyChainTradeAgreementType", propOrder = {
    "sellerCITradeParty",
    "buyerCITradeParty",
    "specifiedProcuringProject"
})
public class CIIHSupplyChainTradeAgreementType {

    @XmlElement(name = "SellerCITradeParty", required = true)
    protected CITradePartyType sellerCITradeParty;
    @XmlElement(name = "BuyerCITradeParty", required = true)
    protected CITradePartyType buyerCITradeParty;
    @XmlElement(name = "SpecifiedProcuringProject")
    protected ProcuringProjectType specifiedProcuringProject;

    /**
     * sellerCITradePartyプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CITradePartyType }
     *     
     */
    public CITradePartyType getSellerCITradeParty() {
        return sellerCITradeParty;
    }

    /**
     * sellerCITradePartyプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CITradePartyType }
     *     
     */
    public void setSellerCITradeParty(CITradePartyType value) {
        this.sellerCITradeParty = value;
    }

    /**
     * buyerCITradePartyプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CITradePartyType }
     *     
     */
    public CITradePartyType getBuyerCITradeParty() {
        return buyerCITradeParty;
    }

    /**
     * buyerCITradePartyプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CITradePartyType }
     *     
     */
    public void setBuyerCITradeParty(CITradePartyType value) {
        this.buyerCITradeParty = value;
    }

    /**
     * specifiedProcuringProjectプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link ProcuringProjectType }
     *     
     */
    public ProcuringProjectType getSpecifiedProcuringProject() {
        return specifiedProcuringProject;
    }

    /**
     * specifiedProcuringProjectプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link ProcuringProjectType }
     *     
     */
    public void setSpecifiedProcuringProject(ProcuringProjectType value) {
        this.specifiedProcuringProject = value;
    }

}
