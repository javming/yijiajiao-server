package com.yijiajiao.server.bean.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurriculumTypeCountBean {

	private int live;
	private double livePrice;
    private int yiduiyi;
    private double yiduiyiPrice;
    private int video;
    private double videoPrice;
    private int answer;
    private double answerPrice;

    
}
