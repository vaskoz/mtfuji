import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TokenPrioritizerTest {

	@Test
	public void checkPriority() {
		//  Some dummy token IDs
		var firstTokenID = "dummy-token-ID-1";
		var secondTokenID = "dummy-token-ID-2";
		var thirdTokenID = "dummy-token-ID-2";

		// Create a couple of test Tokens
		var firstToken = new Token(firstTokenID, 3);
		var secondToken = new Token(secondTokenID, 1);
		var thirdToken = new Token(thirdTokenID, 1);

		//Add the tokens to the prioritizer
		var prioritizer = TokenPrioritizer.create();
		prioritizer.addToken(firstToken);
		prioritizer.addToken(secondToken);

		//Confirm that we get the correct token back from the prioritizer
		var returnedToken = prioritizer.nextToken();
		assertTrue(returnedToken.getTokenID().equals(secondTokenID));
	}

	@Test
	public void moreSophisticatedTest() {
		var prioritizer = TokenPrioritizer.create();
		var fifth = new Token("vasko", 3);
		var fourth = new Token("somebody else", 2);
		var first = new Token("java", 1);
		var second = new Token("interview", 1);
		var third = new Token("three 1s", 1);
		prioritizer.addToken(fourth);
		prioritizer.addToken(first);
		prioritizer.addToken(fifth);
		prioritizer.addToken(second);
		prioritizer.addToken(third);

		assertEquals(first, prioritizer.nextToken());
		assertEquals(second, prioritizer.nextToken());
		assertEquals(third, prioritizer.nextToken());
		assertEquals(fourth, prioritizer.nextToken());
		assertEquals(fifth, prioritizer.nextToken());
	}
}
