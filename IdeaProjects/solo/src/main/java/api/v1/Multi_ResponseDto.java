package api.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Multi_ResponseDto<Member_Dto> {
    private List<Member_Dto> list;
}
