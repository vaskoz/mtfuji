import org.junit.jupiter.api.Test;

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
		prioritizer.addToken( firstToken );
		prioritizer.addToken( secondToken );

		//Confirm that we get the correct token back from the prioritizer
		var returnedToken = prioritizer.nextToken();
		assertTrue( returnedToken.getTokenID().equals( secondTokenID ) );
	}
}
