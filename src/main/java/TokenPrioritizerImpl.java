import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

class TokenPrioritizerImpl implements TokenPrioritizer {
	private static final Comparator<OrderedToken> DEFAULT_COMPARATOR = Comparator.comparingInt(OrderedToken::getPriority)
			.thenComparingLong(OrderedToken::getInsertionOrder);
	private final PriorityBlockingQueue<OrderedToken> queue;
	private final AtomicLong insertionOrder = new AtomicLong();

	private static class OrderedToken {
		private final Token token;
		private final Long insertionOrder;

		public OrderedToken(Token token, Long insertionOrder) {
			this.token = Objects.requireNonNull(token);
			this.insertionOrder = insertionOrder;
		}

		public Token getToken() {
			return token;
		}

		public Long getInsertionOrder() {
			return insertionOrder;
		}

		public int getPriority() {
			return this.token.getPriority();
		}
	}

	TokenPrioritizerImpl() {
		this.queue = new PriorityBlockingQueue<>(11, DEFAULT_COMPARATOR);
	}

	@Override
	public Token nextToken() {
		return Objects.requireNonNull(this.queue.poll()).getToken();
	}

	@Override
	public void addToken(Token theToken) {
		this.queue.add(new OrderedToken(theToken, this.insertionOrder.getAndIncrement()));
	}
}

