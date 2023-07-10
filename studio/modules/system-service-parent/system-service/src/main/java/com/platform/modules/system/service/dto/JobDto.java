
package com.platform.modules.system.service.dto;

import lombok.*;
import com.platform.base.BaseDTO;

import java.io.Serializable;

/**
* @author AllDataDC
* @date 2023-01-27
*/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDto extends BaseDTO implements Serializable {

    private Long id;

    private Integer jobSort;

    private String name;

    private Boolean enabled;

}
