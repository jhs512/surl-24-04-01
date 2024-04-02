/**
 * This file was auto-generated by openapi-typescript.
 * Do not make direct changes to the file.
 */


export interface paths {
  "/api/v1/surls/{id}": {
    /** SURL 단건조회 */
    get: operations["getSurl"];
    /** SURL 등록 */
    put: operations["modify"];
    delete: operations["delete"];
  };
  "/api/v1/surls": {
    /** SURL 목록, 검색가능 */
    get: operations["getSurls"];
    /** SURL 등록 */
    post: operations["create"];
  };
  "/api/v1/members/logout": {
    /** 로그아웃 */
    post: operations["logout"];
  };
  "/api/v1/members/login": {
    /** 로그인, accessToken, refreshToken 쿠키 생성됨 */
    post: operations["login"];
  };
  "/api/v1/members/me": {
    /** 내 정보 */
    get: operations["getMe"];
  };
}

export type webhooks = Record<string, never>;

export interface components {
  schemas: {
    Empty: Record<string, never>;
    RsDataEmpty: {
      resultCode: string;
      /** Format: int32 */
      statusCode: number;
      msg: string;
      data: components["schemas"]["Empty"];
    };
    ModifySurlRequestBody: {
      title: string;
      body: string;
    };
    ModifySurlResponseBody: {
      shortUrl?: string;
    };
    RsDataModifySurlResponseBody: {
      resultCode: string;
      /** Format: int32 */
      statusCode: number;
      msg: string;
      data: components["schemas"]["ModifySurlResponseBody"];
    };
    CreateSurlRequestBody: {
      title: string;
      body: string;
      url: string;
    };
    CreateSurlResponseBody: {
      shortUrl?: string;
    };
    RsDataCreateSurlResponseBody: {
      resultCode: string;
      /** Format: int32 */
      statusCode: number;
      msg: string;
      data: components["schemas"]["CreateSurlResponseBody"];
    };
    LoginRequestBody: {
      username: string;
      password: string;
    };
    LoginResponseBody: {
      item: components["schemas"]["MemberDto"];
    };
    MemberDto: {
      /** Format: int64 */
      id: number;
      /** Format: date-time */
      createDate: string;
      /** Format: date-time */
      modifyDate: string;
      name: string;
      profileImgUrl: string;
      authorities: string[];
      social: boolean;
    };
    RsDataLoginResponseBody: {
      resultCode: string;
      /** Format: int32 */
      statusCode: number;
      msg: string;
      data: components["schemas"]["LoginResponseBody"];
    };
    GetSurlsResponseBody: {
      itemPage: components["schemas"]["PageDtoSurlDto"];
    };
    PageDtoSurlDto: {
      /** Format: int64 */
      totalElementsCount: number;
      /** Format: int64 */
      pageElementsCount: number;
      /** Format: int64 */
      totalPagesCount: number;
      /** Format: int32 */
      number: number;
      content: components["schemas"]["SurlDto"][];
    };
    RsDataGetSurlsResponseBody: {
      resultCode: string;
      /** Format: int32 */
      statusCode: number;
      msg: string;
      data: components["schemas"]["GetSurlsResponseBody"];
    };
    SurlDto: {
      /** Format: int64 */
      id: number;
      /** Format: date-time */
      createDate: string;
      /** Format: date-time */
      modifyDate: string;
      /** Format: int64 */
      authorId: number;
      url: string;
      title: string;
      body: string;
    };
    GetSurlResponseBody: {
      item: components["schemas"]["SurlDto"];
    };
    RsDataGetSurlResponseBody: {
      resultCode: string;
      /** Format: int32 */
      statusCode: number;
      msg: string;
      data: components["schemas"]["GetSurlResponseBody"];
    };
    MeResponseBody: {
      item: components["schemas"]["MemberDto"];
    };
    RsDataMeResponseBody: {
      resultCode: string;
      /** Format: int32 */
      statusCode: number;
      msg: string;
      data: components["schemas"]["MeResponseBody"];
    };
    DeleteSurlResponseBody: {
      shortUrl?: string;
    };
    RsDataDeleteSurlResponseBody: {
      resultCode: string;
      /** Format: int32 */
      statusCode: number;
      msg: string;
      data: components["schemas"]["DeleteSurlResponseBody"];
    };
  };
  responses: never;
  parameters: never;
  requestBodies: never;
  headers: never;
  pathItems: never;
}

export type $defs = Record<string, never>;

export type external = Record<string, never>;

export interface operations {

  /** SURL 단건조회 */
  getSurl: {
    parameters: {
      path: {
        id: number;
      };
    };
    responses: {
      /** @description OK */
      200: {
        content: {
          "application/json": components["schemas"]["RsDataGetSurlResponseBody"];
        };
      };
      /** @description Bad Request */
      400: {
        content: {
          "application/json": components["schemas"]["RsDataEmpty"];
        };
      };
    };
  };
  /** SURL 등록 */
  modify: {
    parameters: {
      path: {
        id: number;
      };
    };
    requestBody: {
      content: {
        "application/json": components["schemas"]["ModifySurlRequestBody"];
      };
    };
    responses: {
      /** @description OK */
      200: {
        content: {
          "application/json": components["schemas"]["RsDataModifySurlResponseBody"];
        };
      };
      /** @description Bad Request */
      400: {
        content: {
          "application/json": components["schemas"]["RsDataEmpty"];
        };
      };
    };
  };
  delete: {
    parameters: {
      path: {
        id: number;
      };
    };
    responses: {
      /** @description OK */
      200: {
        content: {
          "application/json": components["schemas"]["RsDataDeleteSurlResponseBody"];
        };
      };
      /** @description Bad Request */
      400: {
        content: {
          "application/json": components["schemas"]["RsDataEmpty"];
        };
      };
    };
  };
  /** SURL 목록, 검색가능 */
  getSurls: {
    parameters: {
      query?: {
        page?: number;
        kw?: string;
        kwType?: "ALL" | "TITLE" | "BODY" | "URL";
      };
    };
    responses: {
      /** @description OK */
      200: {
        content: {
          "application/json": components["schemas"]["RsDataGetSurlsResponseBody"];
        };
      };
      /** @description Bad Request */
      400: {
        content: {
          "application/json": components["schemas"]["RsDataEmpty"];
        };
      };
    };
  };
  /** SURL 등록 */
  create: {
    requestBody: {
      content: {
        "application/json": components["schemas"]["CreateSurlRequestBody"];
      };
    };
    responses: {
      /** @description OK */
      200: {
        content: {
          "application/json": components["schemas"]["RsDataCreateSurlResponseBody"];
        };
      };
      /** @description Bad Request */
      400: {
        content: {
          "application/json": components["schemas"]["RsDataEmpty"];
        };
      };
    };
  };
  /** 로그아웃 */
  logout: {
    responses: {
      /** @description OK */
      200: {
        content: {
          "application/json": components["schemas"]["RsDataEmpty"];
        };
      };
      /** @description Bad Request */
      400: {
        content: {
          "application/json": components["schemas"]["RsDataEmpty"];
        };
      };
    };
  };
  /** 로그인, accessToken, refreshToken 쿠키 생성됨 */
  login: {
    requestBody: {
      content: {
        "application/json": components["schemas"]["LoginRequestBody"];
      };
    };
    responses: {
      /** @description OK */
      200: {
        content: {
          "application/json": components["schemas"]["RsDataLoginResponseBody"];
        };
      };
      /** @description Bad Request */
      400: {
        content: {
          "application/json": components["schemas"]["RsDataEmpty"];
        };
      };
    };
  };
  /** 내 정보 */
  getMe: {
    responses: {
      /** @description OK */
      200: {
        content: {
          "application/json": components["schemas"]["RsDataMeResponseBody"];
        };
      };
      /** @description Bad Request */
      400: {
        content: {
          "application/json": components["schemas"]["RsDataEmpty"];
        };
      };
    };
  };
}