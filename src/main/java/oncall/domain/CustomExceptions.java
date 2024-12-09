package oncall.domain;

public enum CustomExceptions {
	
	
	OVER_MAX_EMERGENCY_WORKER_NAME(
			"비상근무자의 이름은 최대 %d자 입니다.",
			IllegalArgumentException.class
	),
	ILLEGAL_ARGUMENT(
			"잘못된 입력입니다. 다시 입력해 주세요.",
			IllegalArgumentException.class
	),
	OVER_MAX_RETRY_ATTEPMT(
			"최대 재시도 회수를 초과했습니다.",
			IllegalStateException.class
	),
	FILE_NOT_READABLE(
			"파일 읽기 중 오류가 발생했습니다.",
			IllegalStateException.class
	),
	;
	
	private final String message;
	private final Class<? extends RuntimeException> exceptionType;
	
	CustomExceptions(String message, Class<? extends RuntimeException> exceptionType) {
		this.message = message;
		this.exceptionType = exceptionType;
	}
	
	public RuntimeException get(Object... args) {
		try {
			return exceptionType.getDeclaredConstructor(String.class).newInstance(message.formatted(args));
		} catch (Exception e) {
			return new RuntimeException(message);
		}
	}
}
