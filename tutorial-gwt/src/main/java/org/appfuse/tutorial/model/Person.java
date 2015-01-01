package org.appfuse.tutorial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.appfuse.model.BaseObject;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 * Person entity.
 * 
 * @author ivangsa
 */
@Entity
@Table(name = "person")
@Indexed
@XmlRootElement
public class Person extends BaseObject {

    private static final long serialVersionUID = 5741310862422643253L;

    private Long id;
    private Integer version;
    @NotNull
    @Size(max = 100)
    private String firstName;
    @NotNull
    @Size(max = 150)
    private String lastName;

    // START SNIPPET: id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DocumentId
    public Long getId() {
        return id;
    }

    // END SNIPET: id

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name = "first_name", length = 50)
    @Field
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", length = 50)
    @Field
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Person person = (Person) o;

        if (firstName != null ? !firstName.equals(person.firstName)
                : person.firstName != null)
            return false;
        if (lastName != null ? !lastName.equals(person.lastName)
                : person.lastName != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    public String toString() {
        return "Person{" + "id=" + id + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\'' + '}';
    }
}
