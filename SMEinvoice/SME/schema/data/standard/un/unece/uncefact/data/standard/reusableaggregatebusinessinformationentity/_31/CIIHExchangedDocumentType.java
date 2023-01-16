//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2023.01.16 時間 12:39:23 PM JST 
//


package un.unece.uncefact.data.standard.reusableaggregatebusinessinformationentity._31;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.CodeType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.DateTimeType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.IDType;


/**
 * <p>CIIHExchangedDocumentType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CIIHExchangedDocumentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}IDType"/>
 *         &lt;element name="TypeCode" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}CodeType"/>
 *         &lt;element name="IssueDateTime" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}DateTimeType"/>
 *         &lt;element name="IncludedCINote" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CINoteType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ReferenceCIReferencedDocument" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIReferencedDocumentType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AttachedSpecifiedBinaryFile" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}SpecifiedBinaryFileType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIIHExchangedDocumentType", propOrder = {
    "id",
    "typeCode",
    "issueDateTime",
    "includedCINote",
    "referenceCIReferencedDocument",
    "attachedSpecifiedBinaryFile"
})
public class CIIHExchangedDocumentType {

    @XmlElement(name = "ID", required = true)
    protected IDType id;
    @XmlElement(name = "TypeCode", required = true)
    protected CodeType typeCode;
    @XmlElement(name = "IssueDateTime", required = true)
    protected DateTimeType issueDateTime;
    @XmlElement(name = "IncludedCINote")
    protected List<CINoteType> includedCINote;
    @XmlElement(name = "ReferenceCIReferencedDocument")
    protected List<CIReferencedDocumentType> referenceCIReferencedDocument;
    @XmlElement(name = "AttachedSpecifiedBinaryFile")
    protected List<SpecifiedBinaryFileType> attachedSpecifiedBinaryFile;

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
     * Gets the value of the includedCINote property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the includedCINote property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncludedCINote().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CINoteType }
     * 
     * 
     */
    public List<CINoteType> getIncludedCINote() {
        if (includedCINote == null) {
            includedCINote = new ArrayList<CINoteType>();
        }
        return this.includedCINote;
    }

    /**
     * Gets the value of the referenceCIReferencedDocument property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referenceCIReferencedDocument property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferenceCIReferencedDocument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIReferencedDocumentType }
     * 
     * 
     */
    public List<CIReferencedDocumentType> getReferenceCIReferencedDocument() {
        if (referenceCIReferencedDocument == null) {
            referenceCIReferencedDocument = new ArrayList<CIReferencedDocumentType>();
        }
        return this.referenceCIReferencedDocument;
    }

    /**
     * Gets the value of the attachedSpecifiedBinaryFile property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attachedSpecifiedBinaryFile property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttachedSpecifiedBinaryFile().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpecifiedBinaryFileType }
     * 
     * 
     */
    public List<SpecifiedBinaryFileType> getAttachedSpecifiedBinaryFile() {
        if (attachedSpecifiedBinaryFile == null) {
            attachedSpecifiedBinaryFile = new ArrayList<SpecifiedBinaryFileType>();
        }
        return this.attachedSpecifiedBinaryFile;
    }

}
