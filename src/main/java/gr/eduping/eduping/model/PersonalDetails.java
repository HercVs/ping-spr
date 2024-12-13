package gr.eduping.eduping.model;

import gr.eduping.eduping.core.enums.Gender;
import gr.eduping.eduping.core.enums.Occupation;
import gr.eduping.eduping.model.static_data.City;
import gr.eduping.eduping.model.static_data.Country;
import gr.eduping.eduping.model.static_data.Region;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "personal_details")
public class PersonalDetails extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO ? Allow register without personal details ???

//    @Column(nullable = false)
    private String firstname;

//    @Column(nullable = false)
    private String lastname;

//    @Column(nullable = false)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

//    @Column(nullable = false)
    private Occupation occupation;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
