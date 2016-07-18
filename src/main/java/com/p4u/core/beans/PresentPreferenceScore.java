package com.p4u.core.beans;

import com.p4u.core.model.Present;

public class PresentPreferenceScore implements Comparable<PresentPreferenceScore> {

	private Present present;

	private Integer score;

	public PresentPreferenceScore(Present present, Integer score) {
		super();
		this.present = present;
		this.score = score;
	}

	public Present getPresent() {
		return present;
	}

	public Integer getScore() {
		return score;
	}

	@Override
	public int compareTo(PresentPreferenceScore o) {
		return o.getScore().compareTo(this.score);
	}

}
