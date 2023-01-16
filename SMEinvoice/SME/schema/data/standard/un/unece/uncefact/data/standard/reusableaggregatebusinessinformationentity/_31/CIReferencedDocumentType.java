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
import un.unece.uncefact.data.standard.unqualifieddatatype._31.DateTimeType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.IDType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.TextType;


/**
 * <p>CIReferencedDocumentType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CIReferencedDocumentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IssuerAssignedID" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}IDType"/>
 *         &lt;element name="IssueDateTime" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}DateTimeType" minOccurs="0"/>
 *         &lt;element name="LineID" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}IDType"/>
 *         &lt;element name="RevisionID" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}IDType" minOccurs="0"/>
 *         &lt;element name="Information" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}TextType" minOccurs="0"/>
 *         &lt;element name="SubordinateLineID" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}IDType"/>
 *         &lt;element name="SubtypeCode" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}CodeType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIReferencedDocumentType", propOrder = {
    "issuerAssignedID",
    "issueDateTime",
    "lineID",
    "revisionID",
    "information",
    "subordinateLineID",
    "subtypeCode"
})
public class CIReferencedDocumentType {

    @XmlElement(name = "IssuerAssignedID", required = true)
    protected IDType issuerAssignedID;
    @XmlElement(name = "IssueDateTime")
    protected DateTimeType issueDateTime;
    @XmlElement(name = "LineID", required = true)
    protected IDType lineID;
    @XmlElement(name = "RevisionID")
    protected IDType revisionID;
    @XmlElement(name = "Information")
    protected TextType information;
    @XmlElement(name = "SubordinateLineID", required = true)
    protected IDType subordinateLineID;
    @XmlElement(name = "SubtypeCode")
    protected CodeType subtypeCode;

    /**
     * issuerAssignedIDプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link IDType }
     *     
     */
    public IDType getIssuerAssignedID() {
        return issuerAssignedID;
    }

    /**
     * issuerAssignedIDプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link IDType }
     *     
     */
    public void setIssuerAssignedID(IDType value) {
        this.issuerAssignedID = value;
    }

    /**
     * issueDateTimeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link DateTimeType }
     *     
     */
    public DateTimeType getIssueDateTime() {
        return issueDateTime;
    }

    /**
     * issueDateTimeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeType }
     *     
     */
    public void setIssueDateTime(DateTimeType value) {
        this.issueDateTime = value;
    }

    /**
     * lineIDプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link IDType }
     *     
     */
    public IDType getLineID() {
        return lineID;
    }

    /**
     * lineIDプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link IDType }
     *     
     */
    public void setLineID(IDType value) {
        this.lineID = value;
    }

    /**
     * revisionIDプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link IDType }
     *     
     */
    public IDType getRevisionID() {
        return revisionID;
    }

    /**
     * revisionIDプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link IDType }
     *     
     */
    public void setRevisionID(IDType value) {
        this.revisionID = value;
    }

    /**
     * informationプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getInformation() {
        return information;
    }

    /**
     * informationプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setInformation(TextType value) {
        this.information = value;
    }

    /**
     * subordinateLineIDプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link IDType }
     *     
     */
    public IDType getSubordinateLineID() {
        return subordinateLineID;
    }

    /**
     * subordinateLineIDプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link IDType }
     *     
     */
    public void setSubordinateLineID(IDType value) {
        this.subordinateLineID = value;
    }

    /**
     * subtypeCodeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getSubtypeCode() {
        return subtypeCode;
    }

    /**
     * subtypeCodeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setSubtypeCode(CodeType value) {
        this.subtypeCode = value;
    }

}
