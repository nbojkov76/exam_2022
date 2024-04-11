package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class LibraryMemberIdDto {
@XmlElement
    private Long id;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
