package school.healthboard.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrwalResponseDto {
    private String title;
    private String url;
    private String views;
    private String thumnail;
//    private String channel;
}
