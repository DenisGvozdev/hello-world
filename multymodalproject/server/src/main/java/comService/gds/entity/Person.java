package comService.gds.entity;

//@Entity
//@Table(name="person_serial")
public class Person {

//    @Id
    private Long id;

//    @Column(name="name")
    private String name;

    public Person(){}

    public Person(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
