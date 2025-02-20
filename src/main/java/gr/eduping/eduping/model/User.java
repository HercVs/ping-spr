package gr.eduping.eduping.model;

import gr.eduping.eduping.core.enums.Role;
import gr.eduping.eduping.model.static_data.Department;
import gr.eduping.eduping.model.static_data.StudyField;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User extends AbstractEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_active")
    @ColumnDefault("true")
    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "personal_details_id")
    private PersonalDetails personalDetails;

    @Getter(AccessLevel.PRIVATE)
    @ManyToMany
    @JoinTable(name = "users_study_fields")
    private Set<StudyField> studyFields = new HashSet<>();

    @Getter(AccessLevel.PRIVATE)
    @ManyToMany
    @JoinTable(name = "users_departments")
    private Set<Department> departments;

    public Set<StudyField> getAllStudyFields() {
        if (studyFields == null) studyFields = new HashSet<>();
        return Collections.unmodifiableSet(studyFields);
    }

    public Set<Department> getAllDepartments() {
        if (departments == null) departments = new HashSet<>();
        return Collections.unmodifiableSet(departments);
    }

    public void addDepartment(Department department) {
        if (departments == null) departments = new HashSet<>();
        departments.add(department);
    }

    public void removeDepartment(Department department) {
        if (departments != null) {
            departments.remove(department);
        }
    }

    /**
     * Convenient method to check if there are missing personal details.
     * @return true if there is/are missing personal detail field(s)
     */
    public boolean hasMissingPersonalDetails() {
        return (personalDetails.getFirstname() == null || personalDetails.getFirstname().isBlank()) ||
                (personalDetails.getLastname() == null || personalDetails.getLastname().isBlank()) ||
                personalDetails.getGender() == null ||
                personalDetails.getCity() == null ||
                personalDetails.getCountry() == null ||
                personalDetails.getOccupation() == null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getIsActive() == null || getIsActive();
    }
}
