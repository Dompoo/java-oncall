package oncall.common.exception;

public enum CustomExceptions {
	
	ILLEGAL_ARGUMENT(
			"유효하지 않은 입력 값입니다. 다시 입력해 주세요.",
			IllegalArgumentException.class
	),
	OVER_MAX_RETRY_ATTEPMT(
			"최대 재시도 횟수를 초과했습니다. 프로그램을 종료합니다.",
			IllegalStateException.class
	),
	;
	
	private final String message;
	private final Class<? extends RuntimeException> exceptionType;
	
	CustomExceptions(String message, Class<? extends RuntimeException> exceptionType) {
		this.message = message;
		this.exceptionType = exceptionType;
	}
	
	public RuntimeException get() {
		try {
			return exceptionType.getDeclaredConstructor(String.class).newInstance(message);
		} catch (Exception e) {
			return new RuntimeException(message);
		}
	}
}
