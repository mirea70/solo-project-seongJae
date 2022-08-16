package api.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class Bz_Service {

    private final Bz_Repository bz_repository;

    public List<Member_Dto> find_All() {
        List<Member> list = bz_repository.findAll();
        List<Member_Dto> finds = list.stream()
                .map(member -> Member_Dto.builder()
                        .name(member.getName())
                        .sex(member.getSex())
                        .company_name(member.getCompanyName())
                        .company_type(member.getCompanyType())
                        .company_location(member.getCompanyLocation())
                        .build()
                ).collect(Collectors.toList());
        return finds;
    }

    public List<Member_Dto> findIf_location(String location_Code) {
        List<Member> list = bz_repository.findByCompanyLocationContaining(location_Code);
        List<Member_Dto> finds = list.stream()
                .map(member -> Member_Dto.builder()
                        .name(member.getName())
                        .sex(member.getSex())
                        .company_name(member.getCompanyName())
                        .company_type(member.getCompanyType())
                        .company_location(member.getCompanyLocation())
                        .build()
                ).collect(Collectors.toList());
        return finds;
    }

    public List<Member_Dto> findIf_type(String type_Code) {
        List<Member> list = bz_repository.findByCompanyTypeContaining(type_Code);
        List<Member_Dto> finds = list.stream()
                .map(member -> Member_Dto.builder()
                        .name(member.getName())
                        .sex(member.getSex())
                        .company_name(member.getCompanyName())
                        .company_type(member.getCompanyType())
                        .company_location(member.getCompanyLocation())
                        .build()
                ).collect(Collectors.toList());
        return finds;
    }
}
