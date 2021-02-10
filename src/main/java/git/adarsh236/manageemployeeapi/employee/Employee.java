package git.adarsh236.manageemployeeapi.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String email;
    private String jobTitle;
    private String phone;
    private String imageUrl;
    @Column(nullable = false, updatable = false)
    private String employeeCode;

    public String toString(){
        return "Employee{"
                +"id=" +id
                +", name="+ name+'\''
                +", jobTitle="+ jobTitle+'\''
                +", phone="+phone+'\''
                +", imageUrl="+imageUrl+'\''
                +"}";
    }
}
