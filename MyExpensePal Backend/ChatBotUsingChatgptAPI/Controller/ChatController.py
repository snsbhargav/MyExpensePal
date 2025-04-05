import uvicorn
from fastapi import FastAPI, Header

from Models.UserInput import UserInput
from Models.ModelResponse import ModelResponse
from Services.ChatService import extract_data, proceed_for_cud

app = FastAPI()


@app.post("/input_query")
async def extract_expense_data(expense_query: UserInput, userId: str = Header("userId")):
    return extract_data(expense_query, userId)


# No need for this method remove it
@app.get("/user_confirmation/{confirmation}")
async def do_operation_if_user_confirms(confirmation: bool, modelResponse: ModelResponse,
                                        userId: str = Header("userId")):
    if confirmation:
        return proceed_for_cud(modelResponse, userId)
    else:
        # TODO return no changes are made
        return "No Changes made."


if __name__ == "__main__":
    uvicorn.run(app, host="localhost", port=8087)

# @app.get("/")
# async def test():
#     return "test"
