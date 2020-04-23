package client.model.village;

import java.time.Duration;
import java.time.LocalDate;

public abstract class Guard {
	private LocalDate guardEnds;
	public abstract Duration guardDuration();
	public abstract boolean isGuarded();
}
