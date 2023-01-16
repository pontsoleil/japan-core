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
import un.unece.uncefact.data.standard.unqualifieddatatype._31.IDType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.TextType;


/**
 * <p>CIDocumentContextParameterType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CIDocumentContextParameterType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}IDType" minOccurs="0"/>
 *         &lt;element name="Value" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}TextType" minOccurs="0"/>
 *         &lt;element name="SpecifiedCIDocumentVersion" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIDocumentVersionType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIDocumentContextParameterType", propOrder = {
    "id",
    "value",
    "specifiedCIDocumentVersion"
})
public class CIDocumentContextParameterType {

    @XmlElement(name = "ID")
    protected IDType id;
    @XmlElement(name = "Value")
    protected TextType value;
    @XmlElement(name = "SpecifiedCIDocumentVersion", required = true)
    protected CIDocumentVersionType specifiedCIDocumentVersion;

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
     * valueプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getValue() {
        return value;
    }

    /**
     * valueプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setValue(TextType value) {
        this.value = value;
    }

    /**
     * specifiedCIDocumentVersionプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIDocumentVersionType }
     *     
     */
    public CIDocumentVersionType getSpecifiedCIDocumentVersion() {
        return specifiedCIDocumentVersion;
    }

    /**
     * specifiedCIDocumentVersionプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIDocumentVersionType }
     *     
     */
    public void setSpecifiedCIDocumentVersion(CIDocumentVersionType value) {
        this.specifiedCIDocumentVersion = value;
    }

}
