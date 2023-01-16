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
 * <p>CIExchangedDocumentContextType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CIExchangedDocumentContextType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BusinessProcessSpecifiedCIDocumentContextParameter" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIDocumentContextParameterType"/>
 *         &lt;element name="SubsetSpecifiedCIDocumentContextParameter" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIDocumentContextParameterType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIExchangedDocumentContextType", propOrder = {
    "businessProcessSpecifiedCIDocumentContextParameter",
    "subsetSpecifiedCIDocumentContextParameter"
})
public class CIExchangedDocumentContextType {

    @XmlElement(name = "BusinessProcessSpecifiedCIDocumentContextParameter", required = true)
    protected CIDocumentContextParameterType businessProcessSpecifiedCIDocumentContextParameter;
    @XmlElement(name = "SubsetSpecifiedCIDocumentContextParameter", required = true)
    protected CIDocumentContextParameterType subsetSpecifiedCIDocumentContextParameter;

    /**
     * businessProcessSpecifiedCIDocumentContextParameterプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIDocumentContextParameterType }
     *     
     */
    public CIDocumentContextParameterType getBusinessProcessSpecifiedCIDocumentContextParameter() {
        return businessProcessSpecifiedCIDocumentContextParameter;
    }

    /**
     * businessProcessSpecifiedCIDocumentContextParameterプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIDocumentContextParameterType }
     *     
     */
    public void setBusinessProcessSpecifiedCIDocumentContextParameter(CIDocumentContextParameterType value) {
        this.businessProcessSpecifiedCIDocumentContextParameter = value;
    }

    /**
     * subsetSpecifiedCIDocumentContextParameterプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIDocumentContextParameterType }
     *     
     */
    public CIDocumentContextParameterType getSubsetSpecifiedCIDocumentContextParameter() {
        return subsetSpecifiedCIDocumentContextParameter;
    }

    /**
     * subsetSpecifiedCIDocumentContextParameterプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIDocumentContextParameterType }
     *     
     */
    public void setSubsetSpecifiedCIDocumentContextParameter(CIDocumentContextParameterType value) {
        this.subsetSpecifiedCIDocumentContextParameter = value;
    }

}
