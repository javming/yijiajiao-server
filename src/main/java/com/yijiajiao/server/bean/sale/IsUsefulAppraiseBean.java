package com.yijiajiao.server.bean.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IsUsefulAppraiseBean {

  private String openId;
  private int pid;

}
