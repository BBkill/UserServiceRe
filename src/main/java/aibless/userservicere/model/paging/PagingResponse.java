package aibless.userservicere.model.paging;

import aibless.userservicere.dto.UserResponseDto;
import aibless.userservicere.model.User;
import lombok.Data;

import javax.validation.constraints.Min;
import java.util.List;

@Data
public class PagingResponse {
    public static final int PAGE_SIZE = 10;
    public static final int PAGE_NO = 1;

    private List<UserResponseDto> users;

    @Min(1)
    private Integer pageSize = PAGE_SIZE;

    @Min(1)
    private Integer pageNo = PAGE_NO;

    private Integer total;


}
