package dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class History_DTO {

    private int id;
    private String lat;
    private String lnt;
    private String searchDttm;
}