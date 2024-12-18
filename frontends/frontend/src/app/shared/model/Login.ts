export interface LoginRequest {
  username: string;
  password: string;
}

export interface JwtAuthenticationResponse {
  token: string;
}
export interface JwtAuthenticationRequest {
  token: string;
}
