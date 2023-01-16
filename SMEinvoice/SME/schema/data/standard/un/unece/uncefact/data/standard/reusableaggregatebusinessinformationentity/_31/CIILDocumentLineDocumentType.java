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
import un.unece.uncefact.data.standard.unqualifieddatatype._31.IDType;


/**
 * <p>CIILDocumentLineDocumentType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CIILDocumentLineDocumentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LineID" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}IDType"/>
 *         &lt;element name="CategoryCode" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}CodeType"/>
 *         &lt;element name="IncludedCINote" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CINoteType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ReferenceCIReferencedDocument" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIReferencedDocumentType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIILDocumentLineDocumentType", propOrder = {
    "lineID",
    "categoryCode",
    "includedCINote",
    "referenceCIReferencedDocument"
})
public class CIILDocumentLineDocumentType {

    @XmlElement(name = "LineID", required = true)
    protected IDType lineID;
    @XmlElement(name = "CategoryCode", required = true)
    protected CodeType categoryCode;
    @XmlElement(name = "IncludedCINote")
    protected List<CINoteType> includedCINote;
    @XmlElement(name = "ReferenceCIReferencedDocument")
    protected CIReferencedDocumentType referenceCIReferencedDocument;

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
     * referenceCIReferencedDocumentプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIReferencedDocumentType }
     *     
     */
    public CIReferencedDocumentType getReferenceCIReferencedDocument() {
        return referenceCIReferencedDocument;
    }

    /**
     * referenceCIReferencedDocumentプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIReferencedDocumentType }
     *     
     */
    public void setReferenceCIReferencedDocument(CIReferencedDocumentType value) {
        this.referenceCIReferencedDocument = value;
    }

}
