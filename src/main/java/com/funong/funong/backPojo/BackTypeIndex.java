package com.funong.funong.backPojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackTypeIndex {
    private int start;
    private int end;
    private String type;
    private int id;
    private int page;
}
