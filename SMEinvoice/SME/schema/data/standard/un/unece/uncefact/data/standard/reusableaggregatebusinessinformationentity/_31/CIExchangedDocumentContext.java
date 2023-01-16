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
import un.unece.uncefact.data.standard.unqualifieddatatype._31.DateTimeType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.IDType;


/**
 * <p>CIExchangedDocumentContext complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CIExchangedDocumentContext">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SpecifiedTransactionID" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}IDType" minOccurs="0"/>
 *         &lt;element name="ProcessingTransactionDateTime" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}DateTimeType" minOccurs="0"/>
 *         &lt;element name="BusinessProcessSpecifiedCIDocumentContextParameter" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIDocumentContextParameterType"/>
 *         &lt;element name="ScenarioSpecifiedCIDocumentContextParameter" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIDocumentContextParameterType" minOccurs="0"/>
 *         &lt;element name="ApplicationSpecifiedCIDocumentContextParameter" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIDocumentContextParameterType" minOccurs="0"/>
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
@XmlType(name = "CIExchangedDocumentContext", propOrder = {
    "specifiedTransactionID",
    "processingTransactionDateTime",
    "businessProcessSpecifiedCIDocumentContextParameter",
    "scenarioSpecifiedCIDocumentContextParameter",
    "applicationSpecifiedCIDocumentContextParameter",
    "subsetSpecifiedCIDocumentContextParameter"
})
public class CIExchangedDocumentContext {

    @XmlElement(name = "SpecifiedTransactionID")
    protected IDType specifiedTransactionID;
    @XmlElement(name = "ProcessingTransactionDateTime")
    protected DateTimeType processingTransactionDateTime;
    @XmlElement(name = "BusinessProcessSpecifiedCIDocumentContextParameter", required = true)
    protected CIDocumentContextParameterType businessProcessSpecifiedCIDocumentContextParameter;
    @XmlElement(name = "ScenarioSpecifiedCIDocumentContextParameter")
    protected CIDocumentContextParameterType scenarioSpecifiedCIDocumentContextParameter;
    @XmlElement(name = "ApplicationSpecifiedCIDocumentContextParameter")
    protected CIDocumentContextParameterType applicationSpecifiedCIDocumentContextParameter;
    @XmlElement(name = "SubsetSpecifiedCIDocumentContextParameter", required = true)
    protected CIDocumentContextParameterType subsetSpecifiedCIDocumentContextParameter;

    /**
     * specifiedTransactionIDプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link IDType }
     *     
     */
    public IDType getSpecifiedTransactionID() {
        return specifiedTransactionID;
    }

    /**
     * specifiedTransactionIDプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link IDType }
     *     
     */
    public void setSpecifiedTransactionID(IDType value) {
        this.specifiedTransactionID = value;
    }

    /**
     * processingTransactionDateTimeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link DateTimeType }
     *     
     */
    public DateTimeType getProcessingTransactionDateTime() {
        return processingTransactionDateTime;
    }

    /**
     * processingTransactionDateTimeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeType }
     *     
     */
    public void setProcessingTransactionDateTime(DateTimeType value) {
        this.processingTransactionDateTime = value;
    }

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
     * scenarioSpecifiedCIDocumentContextParameterプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIDocumentContextParameterType }
     *     
     */
    public CIDocumentContextParameterType getScenarioSpecifiedCIDocumentContextParameter() {
        return scenarioSpecifiedCIDocumentContextParameter;
    }

    /**
     * scenarioSpecifiedCIDocumentContextParameterプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIDocumentContextParameterType }
     *     
     */
    public void setScenarioSpecifiedCIDocumentContextParameter(CIDocumentContextParameterType value) {
        this.scenarioSpecifiedCIDocumentContextParameter = value;
    }

    /**
     * applicationSpecifiedCIDocumentContextParameterプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIDocumentContextParameterType }
     *     
     */
    public CIDocumentContextParameterType getApplicationSpecifiedCIDocumentContextParameter() {
        return applicationSpecifiedCIDocumentContextParameter;
    }

    /**
     * applicationSpecifiedCIDocumentContextParameterプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIDocumentContextParameterType }
     *     
     */
    public void setApplicationSpecifiedCIDocumentContextParameter(CIDocumentContextParameterType value) {
        this.applicationSpecifiedCIDocumentContextParameter = value;
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
