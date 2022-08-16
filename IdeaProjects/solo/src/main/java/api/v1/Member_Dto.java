package api.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member_Dto {

    private String name;

    private String sex;

    private String company_name;

    private String company_type;

    private String company_location;
}
