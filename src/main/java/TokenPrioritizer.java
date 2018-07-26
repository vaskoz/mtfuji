public interface TokenPrioritizer {
	/**
	 * This method puts a token onto the priority queue.
	 *
	 * @param token the token that will be added to the priority queue
	 */
	void addToken(Token token);

	/**
	 * This method returns the token currently on the queue with
	 * the highest priority. If multiple tokens have the same priority
	 * it will return the first of those tokens to be added to the
	 * queue. May return null if no tokens remain on the queue.
	 *
	 * @return The token on the queue with the highest priority
	 */
	Token nextToken();

	/**
	 * Returns a TokenPrioritizer instance.
	 * Default prioritization is defined by the problem statement.
	 */
	static TokenPrioritizer create() {
		return new TokenPrioritizerImpl();
	}
}

